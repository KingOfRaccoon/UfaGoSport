package ru.skittens.data.source

import ru.skittens.data.util.Postman
import ru.skittens.domain.entity.Country
import ru.skittens.domain.entity.Playground
import ru.skittens.domain.util.Resource

class PlaygroundService(private val postman: Postman) {
    private val baseUrl = "https://www.workout.su/api/v3/"
    private val playgroundTag = "areas"
    private val countriesTag = "countries"

    suspend fun getPlaygrounds(countryId: Int = 1, cityId: Int = 142): Resource<List<Playground>> {
        return postman.get(
            baseUrl, playgroundTag, arguments = mapOf(
                "country_id" to countryId,
                "city_id" to cityId
            )
        )
    }

    suspend fun getCountries(): Resource<List<Country>> {
        return postman.get(baseUrl, countriesTag)
    }
}