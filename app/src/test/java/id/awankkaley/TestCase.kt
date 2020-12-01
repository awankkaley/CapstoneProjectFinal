package id.awankkaley


import id.awankkaley.core.domain.model.Popular
import id.awankkaley.core.domain.repository.IPopularRepository
import id.awankkaley.core.domain.usecase.PopularInteractor
import id.awankkaley.core.domain.usecase.PopularUseCase
import kotlinx.coroutines.flow.Flow
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestCase {

    private lateinit var messageUseCase: PopularUseCase

    @Mock
    private lateinit var messageRepository: IPopularRepository

    @Mock
    private lateinit var flow: Flow<List<Popular>>


    @Before
    fun setUp() {
        messageUseCase = PopularInteractor(messageRepository)
        `when`(messageRepository.getFavoritePopular()).thenReturn(flow)
    }

    @Test
    fun `should get fav data from repository`() {
        messageUseCase.getFavoritePopular()
        verify(messageRepository).getFavoritePopular()
        Mockito.verifyNoMoreInteractions(messageRepository)
    }

}