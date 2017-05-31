package fm.weigl.refsberlin.internal

import org.hamcrest.BaseMatcher
import org.hamcrest.Description

/**
 * Created by asco on 4/19/17.
 */
class ArgThat<T>(val predicate: T.() -> Boolean) : BaseMatcher<T>() {
    override fun describeTo(description: Description) {
        description.appendText("argThat matcher not satisfied")
    }

    @Suppress("UNCHECKED_CAST")
    override fun matches(it: Any): Boolean {
        return (it as T).predicate()
    }
}