package cd.gamedesigner.vijey.stargame

import android.content.ClipData
import android.content.DialogInterface
import android.graphics.Color
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var score : Int = 0
    var imageArray = ArrayList<ImageView>()
    var handler : Handler = Handler()
    var runnable : Runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init(){

        imageViewSamClark.visibility = View.INVISIBLE
        textViewSamClark.visibility = View.INVISIBLE

        score = 0

        imageArray = arrayListOf(imageView, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9,
            imageView10, imageView11, imageView12, imageView13, imageView14, imageView15)

        hideImage()

        time()
    }

    private fun time(){

        object : CountDownTimer(301000, 1000){
            override fun onFinish() {
                tv_time.text = "Time up "
                tv_time.setTextColor(Color.parseColor("#be0072"))

                val mp_game_over = MediaPlayer.create(baseContext, R.raw.clara_game_over)
                mp_game_over.start()

                handler.removeCallbacks(runnable)

                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                imageViewLove.visibility = View.VISIBLE
                imageViewLove1.visibility = View.VISIBLE
                imageViewLove2.visibility = View.VISIBLE
                imageViewLove3.visibility = View.VISIBLE

                imageViewSamClark.visibility = View.VISIBLE
                textViewSamClark.visibility = View.VISIBLE
            }

            override fun onTick(millisUntilFinished: Long) {
                tv_time.text = "Time: " + millisUntilFinished / 1000
            }
        }.start()
    }

    private fun hideImage(){
        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val index = random.nextInt(15-0)
                imageArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable, 400)

                imageViewLove.visibility = View.INVISIBLE
                imageViewLove1.visibility = View.INVISIBLE
                imageViewLove2.visibility = View.INVISIBLE
                imageViewLove3.visibility = View.INVISIBLE

            }
        }
        handler.post(runnable)
    }

    fun increaseScore(view: View){
        score++
        tv_score.text = "Score: $score"

        imageViewLove.visibility = View.VISIBLE
        imageViewLove1.visibility = View.VISIBLE
        imageViewLove2.visibility = View.VISIBLE
        imageViewLove3.visibility = View.VISIBLE

        val mp = MediaPlayer.create(this, R.raw.toucher_clara)
        mp.start()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.restart_menu -> {
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Restart")
                alert.setMessage("Do you want to restart the game ?")
                alert.setPositiveButton("Yes"){
                        dialog: DialogInterface?, which: Int ->

                        tv_time.setTextColor(Color.WHITE)
                        init()
                }
                alert.setNegativeButton("No"){dialog: DialogInterface?, which: Int ->  }
                alert.show()
            }

            R.id.close_menu -> {
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Close")
                alert.setMessage("Do you want to quit the game ?")
                alert.setPositiveButton("Exit"){
                        dialog: DialogInterface?, which: Int ->
                        finish()
                }
                alert.setNegativeButton("No"){dialog: DialogInterface?, which: Int ->  }
                alert.show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
