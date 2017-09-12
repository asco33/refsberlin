package fm.weigl.refsberlin.update.net

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppVersionRepository @Inject constructor(private val appVersionService: AppVersionService) {

    fun appVersion() = appVersionService.appVersion()

}
