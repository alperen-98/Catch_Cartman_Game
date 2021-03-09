package com.app.catch_kotlin


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private var score = 0
    var images = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        images.add(cartman1)
        images.add(cartman2)
        images.add(cartman3)
        images.add(cartman4)
        images.add(cartman5)
        images.add(cartman6)
        images.add(cartman7)
        images.add(cartman8)
        images.add(cartman9)

        hideImages()

        countDownFromThree()


    }// on create end

    private fun play(){
        gameOn()
        countDownFromTen()

    }

    private fun countDownFromThree(){
        object : CountDownTimer(3000, 1000){
            override fun onFinish() {
                play()
            }

            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "${millisUntilFinished/1000}"
            }

        }.start()
    }
    private fun countDownFromTen(){
        object: CountDownTimer(10000, 1000){
            override fun onFinish() {
                restartButton.visibility = View.VISIBLE
                handler.removeCallbacks(runnable)
                hideImages()
            }

            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "Time = ${millisUntilFinished/1000}"

            }
        }.start()
    }
    private fun gameOn(){
        runnable = Runnable {
            hideImages()
            randomlyAppear()
            handler.postDelayed(runnable, 500) //speed of the appear time
        }
        handler.post(runnable)

    }

    private fun hideImages(){
        for(image in images){
            image.visibility = View.INVISIBLE
        }
    }
    private fun randomlyAppear(){
        val random = Random
        var randomNumber : Int = random.nextInt(9)
        images[randomNumber].visibility = View.VISIBLE
    }

    fun increaseScore(view: View){
        score++
        scoreText.text = "Score = $score"
        hideImages()
    }

    fun restart(view: View){
        startActivity(Intent(this, MainActivity::class.java))
    }

}
