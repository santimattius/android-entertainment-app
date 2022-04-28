## Robolectric

Running tests on an Android emulator or device is slow! Building, deploying, and launching the app
often takes a minute or more. That’s no way to do TDD. There must be a better way.

Robolectric is a framework that brings fast and reliable unit tests to Android. Tests run inside the
JVM on your workstation in seconds. With Robolectric you can write tests like this:

```kotlin
@RunWith(RobolectricTestRunner::class)
class MyActivityTest {

    @Test
    fun clickingButton_shouldChangeMessage() {
        val activity = Robolectric.setupActivity(MyActivity::class.java)

        activity.button.performClick()

        assertThat(activity.message.getText(), equalTo(EqualTo("Robolectric Rocks!")))
    }
}

```