package com.example.scanit.di

import android.app.Application
import android.content.Context
import com.example.scanit.data.repository.FirebaseAuthRepositoryImpl
import com.example.scanit.data.repository.ProductsRepositoryImpl
import com.example.scanit.data.repository.ReceiptsRepositoryImpl
import com.example.scanit.domain.repository.BaseAuthRepository
import com.example.scanit.domain.repository.BaseProductsRepository
import com.example.scanit.domain.repository.BaseReceiptsRepository
import com.example.scanit.util.Constants.FIREBASE_GOOGLE_AUTH_CLIENT_ID
import com.example.scanit.util.Constants.RECEIPTS_REF
import com.example.scanit.util.Constants.SIGN_IN_REQ
import com.example.scanit.util.Constants.SIGN_UP_REQ
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(
        app: Application
    ): Context = app.applicationContext

    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    fun provideOneTapClient(
        context: Context
    ) = Identity.getSignInClient(context)

    @Provides
    @Named(SIGN_IN_REQ)
    fun provideSignInRequest() = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(FIREBASE_GOOGLE_AUTH_CLIENT_ID)
                .setFilterByAuthorizedAccounts(true)
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()

    @Provides
    @Named(SIGN_UP_REQ)
    fun provideSignUpRequest() = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(FIREBASE_GOOGLE_AUTH_CLIENT_ID)
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .build()

    @Provides
    fun provideGoogleSignInOptions() =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(FIREBASE_GOOGLE_AUTH_CLIENT_ID)
            .requestEmail()
            .build()

    @Provides
    fun provideGoogleSignInClient(
        app: Application,
        options: GoogleSignInOptions
    ) = GoogleSignIn.getClient(app, options)

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        oneTapClient: SignInClient,
        @Named(SIGN_IN_REQ)
        signInRequest: BeginSignInRequest,
        @Named(SIGN_UP_REQ)
        signUpRequest: BeginSignInRequest,
        signInClient: GoogleSignInClient
    ): BaseAuthRepository = FirebaseAuthRepositoryImpl(
        auth = auth,
        oneTapClient = oneTapClient,
        signInRequest = signInRequest,
        signUpRequest = signUpRequest,
        signInClient = signInClient
    )

    @Provides
    fun provideCurrentUser(
        auth: FirebaseAuth
    ): FirebaseUser? = auth.currentUser

    @Singleton
    @Provides
    fun provideReceiptsRef(
        database: FirebaseFirestore
    ) = database.collection(RECEIPTS_REF)

    @Singleton
    @Provides
    fun provideReceiptsRepository(
        receiptsRef: CollectionReference,
        currentUser: FirebaseUser?,
        productsRepository: BaseProductsRepository
    ): BaseReceiptsRepository = ReceiptsRepositoryImpl(receiptsRef, currentUser, productsRepository)

    @Singleton
    @Provides
    fun provideProductsRepository(
        receiptsRef: CollectionReference
    ) : BaseProductsRepository = ProductsRepositoryImpl(receiptsRef)
}