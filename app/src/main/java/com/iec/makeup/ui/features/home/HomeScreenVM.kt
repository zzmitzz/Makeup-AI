package com.iec.makeup.ui.features.home

import com.iec.makeup.core.BaseViewModel
import com.iec.makeup.core.model.User
import dagger.hilt.android.lifecycle.HiltViewModel


data class HomeScreenState(
    val isLoading: Boolean = false,
    val userProfile: User? = null,
    val error: String? = null,
    val isRefreshing: Boolean = false,

    // Order Section
    val orderToPay: List<String> = emptyList(),
    val orderToReceive: List<String> = emptyList(),
    val orderToReview: List<String> = emptyList(),

    // Reels Section

    val listReels: List<String>

    // Booking Section
    

)


//@HiltViewModel
//class HomeScreenVM : BaseViewModel {
//}