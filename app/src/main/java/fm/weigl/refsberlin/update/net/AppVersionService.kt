package fm.weigl.refsberlin.update.net

import fm.weigl.refdata.appversion.AppVersion
import io.reactivex.Single
import retrofit2.http.GET

interface AppVersionService {
    @GET("/appversion")
    fun appVersion(): Single<AppVersion>

}