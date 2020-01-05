package com.example.simpleslider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val initial_y_translation = status_textview_mainActivity.translationY // saves the initial y pos

        seekBar_mainActivity.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                status_textview_mainActivity.text=progress.toString()

                val translationDistance = (initial_y_translation + progress +(resources.getDimension(R.dimen.text_anim_step)) *(-1))
                /*above the distance that the status text view should be translated is calculated using the initial pos and a dimen
                created in a resource file*/

                status_textview_mainActivity.animate().translationY(translationDistance)

                //now, onProgressChanged, the translation distance will be calculated and the text view will be moved up

                if(!fromUser) {//prevents spamming of the reset button when there is no progress
                    status_textview_mainActivity.animate().setDuration(500).rotationBy(360f)
                        .translationY(initial_y_translation)

                    //above, over the 500ms the text is rotated a full circle and translated back to where is started
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }


        })


        //below, a lambda is implemented every time the reset is pressed, the lambda does the resetting
        reset_button_mainActivity.setOnClickListener{ v ->
            seekBar_mainActivity.progress = 0 //reset the progress to zero
            //now that the progress is back to zero, we should animate the text as needed


        }

    }


}
