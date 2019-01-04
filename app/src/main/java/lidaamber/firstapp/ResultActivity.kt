package lidaamber.firstapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * @author lidaamber
 */
class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        supportActionBar?.title = "Films"
    }
}