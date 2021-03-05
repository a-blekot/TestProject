# TestProject

Concurent access to list: to achive goal click many times on __FAIL__ button.
*Sometimes on click would be enough to crash app with*

<code>
java.lang.ArrayIndexOutOfBoundsException: src.length=10000 srcPos=0 dst.length=50000 dstPos=50000 length=10000
        at java.lang.System.arraycopy(Native Method)
        at java.util.ArrayList.addAll(ArrayList.java:591)
        at com.example.testproject.SecondFragment.loadEmojisFail(SecondFragment.kt:99)
        at com.example.testproject.SecondFragment$onViewCreated$2$1$1.invokeSuspend(SecondFragment.kt:76)
        at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
        at kotlinx.coroutines.DispatchedTask.run(Dispatched.kt:241)
        at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:594)
        at kotlinx.coroutines.scheduling.CoroutineScheduler.access$runSafely(CoroutineScheduler.kt:60)
        at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:740)
        </code>
