package com.example.testproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.coroutines.*
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var emojiListOK: MutableList<EmojiFirebaseApiModel?> = Collections.synchronizedList(mutableListOf())
    private var emojiListFail: MutableList<EmojiFirebaseApiModel?> = mutableListOf()
    private lateinit var fakeListN: MutableList<EmojiFirebaseApiModel?>

    private val backgroundJob = SupervisorJob()
    private val backgroundScope = CoroutineScope(Dispatchers.IO + backgroundJob)

    private fun generateEmoji() = EmojiFirebaseApiModel().apply {
        id = "ID"
        pos = 1
        imageUrlInactive = "urlInactive"
        imageUrlActive = "urlActive"
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fakeListN = mutableListOf()
        for (i in 1..1_000_0) {
            fakeListN.add(generateEmoji())
        }

        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.fragment2_button1).setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {

                val jobs = mutableListOf<Deferred<Boolean>>()
                val N = 200
                for (i in 1..N) {
                    jobs.add(backgroundScope.async {
                        Log.d("LIST_OK", "${Thread.currentThread().name} async $i start")
                        loadEmojisOK()
                    })
                }

                for (i in 0 until N) {
                    val result = jobs[i].await()
                    Log.d("LIST_OK", "${Thread.currentThread().name} result $i $result")
                }
            }
        }

        view.findViewById<Button>(R.id.fragment2_button2).setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {

                val jobs = mutableListOf<Deferred<Boolean>>()
                val N = 200
                for (i in 1..N) {
                    jobs.add(backgroundScope.async {
                        Log.d("LIST_FAIL", "${Thread.currentThread().name} async $i start")
                        loadEmojisFail()
                    })
                }

                for (i in 0 until N) {
                    val result = jobs[i].await()
                    Log.d("LIST_FAIL", "${Thread.currentThread().name} result $i $result")
                }
            }
        }
    }

    fun loadEmojisOK(): Boolean {
        Log.d("LIST_OK", "${Thread.currentThread().name} loadEmojisOK clear")
        emojiListOK.clear()
        Log.d("LIST_OK", "${Thread.currentThread().name} loadEmojisOK addAll")
        return emojiListOK.addAll(fakeListN)
    }

    fun loadEmojisFail(): Boolean {
        Log.d("LIST_FAIL", "${Thread.currentThread().name} loadEmojisFail clear")
        emojiListFail.clear()
        Log.d("LIST_FAIL", "${Thread.currentThread().name} loadEmojisFail addAll")
        return emojiListFail.addAll(fakeListN)
    }
}