package cd.gamedesigner.vijey.stargame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    private fun init(){
        val chrono = object : Thread(){
            override fun run() {
                try {
                    sleep(3000)
                }catch (e: InterruptedException){
                    e.printStackTrace()
                }finally {
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        chrono.start()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
