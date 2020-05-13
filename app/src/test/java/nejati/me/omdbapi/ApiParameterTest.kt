package nejati.me.omdbapi

import org.junit.Assert
import org.junit.Test

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class ApiParameterTest {

    @Test
    fun ApiKeyTest() {
        Assert.assertEquals(
            "a0a61398",
            BuildConfig.API_KEY
        )
    }

}
