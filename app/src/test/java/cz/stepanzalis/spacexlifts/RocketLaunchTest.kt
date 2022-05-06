package cz.stepanzalis.spacexlifts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cz.stepanzalis.spacexlifts.io.db.dao.RocketDao
import cz.stepanzalis.spacexlifts.io.db.dao.RocketLaunchDao
import cz.stepanzalis.spacexlifts.io.db.entities.RocketEntity
import cz.stepanzalis.spacexlifts.io.repositories.SpaceXRepo
import cz.stepanzalis.spacexlifts.io.services.SpaceXApiService
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertEquals
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RocketLaunchTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var spaceXRepo: SpaceXRepo

    @Mock
    private lateinit var apiService: SpaceXApiService

    @Mock
    private lateinit var rocketDao: RocketDao

    @Mock
    private lateinit var rocketLaunchDao: RocketLaunchDao

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        spaceXRepo = SpaceXRepo(rocketLaunchDao, rocketDao, apiService)
    }

    @After
    fun tearDown() {}

    @Test
    fun rocket_detail_not_exists_in_db() {
        val notExistingId = "notExistingId"

        runBlocking {
            `when`(spaceXRepo.getRocketDetail(notExistingId)).thenReturn(null)
            val result = spaceXRepo.getRocketDetail(notExistingId)
            assertEquals(result, null)
        }
    }

    @Test
    fun rocket_detail_returns_right_entity() {
        val id = "1"
        val entity = RocketEntity.mock()

        runBlocking {
            `when`(spaceXRepo.getRocketDetail(id)).thenReturn(entity)
            val result = spaceXRepo.getRocketDetail(id)
            assertEquals(result, entity)
        }
    }
}