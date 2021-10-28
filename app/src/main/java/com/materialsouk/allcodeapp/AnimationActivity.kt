package com.materialsouk.allcodeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class AnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        val image = findViewById<ImageView>(R.id.imageView)
        findViewById<Button>(R.id.blinkBtn).setOnClickListener {
            animationSet(image, R.anim.blink_anim)
        }

        findViewById<Button>(R.id.bounceBtn).setOnClickListener {
            animationSet(image, R.anim.bounce_anim)
        }

        findViewById<Button>(R.id.fade_inBtn).setOnClickListener {
            animationSet(image, R.anim.fade_in_anim)
        }

        findViewById<Button>(R.id.fade_outBtn).setOnClickListener {
            animationSet(image, R.anim.fade_out_anim)
        }

        findViewById<Button>(R.id.moveBtn).setOnClickListener {
            animationSet(image, R.anim.move_anim)
        }

        findViewById<Button>(R.id.rotateBtn).setOnClickListener {
            animationSet(image, R.anim.rotate_anim)
        }

        findViewById<Button>(R.id.sequentialBtn).setOnClickListener {
            animationSet(image, R.anim.sequential_anim)
        }

        findViewById<Button>(R.id.slide_downBtn).setOnClickListener {
            animationSet(image, R.anim.slide_down_anim)
        }

        findViewById<Button>(R.id.slide_upBtn).setOnClickListener {
            animationSet(image, R.anim.slide_up_anim)
        }

        findViewById<Button>(R.id.togetherBtn).setOnClickListener {
            animationSet(image, R.anim.together_anim)
        }

        findViewById<Button>(R.id.zoom_inBtn).setOnClickListener {
            animationSet(image, R.anim.zoom_in_anim)
        }

        findViewById<Button>(R.id.zoom_outBtn).setOnClickListener {
            animationSet(image, R.anim.zoom_out_anim)
        }
    }

    private fun animationSet(image: ImageView, locationAnim: Int) {
        val animation = AnimationUtils.loadAnimation(this, locationAnim)
        image.startAnimation(animation)
    }
}