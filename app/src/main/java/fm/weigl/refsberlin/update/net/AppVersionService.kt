package fm.weigl.refsberlin.update.net

import fm.weigl.refdata.appversion.AppVersion
import rx.Observable

interface AppVersionService {
    @retrofit.http.GET("/appversion")
    fun appVersion(): Observable<AppVersion>

}