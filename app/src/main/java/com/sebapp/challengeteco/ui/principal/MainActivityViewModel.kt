package com.sebapp.challengeteco.ui.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sebapp.challengeteco.data.network.CharacterPagingSource
import com.sebapp.challengeteco.data.network.RetroInstance
import com.sebapp.challengeteco.data.services.RetroService
import com.sebapp.challengeteco.domain.model.CharacterData
import kotlinx.coroutines.flow.Flow


class MainActivityViewModel: ViewModel() {

    private var retroService: RetroService =
        RetroInstance.getRetroInstance().create(RetroService::class.java)

    fun getListData(): Flow<PagingData<CharacterData>> {
        return Pager (config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { CharacterPagingSource(retroService) }).flow.cachedIn(viewModelScope)
    }
}