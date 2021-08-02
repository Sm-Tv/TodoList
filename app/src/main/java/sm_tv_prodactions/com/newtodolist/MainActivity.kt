package sm_tv_prodactions.com.newtodolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private lateinit var botoomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navOptions = NavOptions.Builder().setPopUpTo(R.id.listFragment, true).build()


        botoomNavView = findViewById(R.id.botoomNavView)
       //setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))
        setupActionBarWithNavController(findNavController(R.id.fragment))




        botoomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.shopping_list -> {
                    findNavController(R.id.fragment).navigate(R.id.shopingListFragment)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    //botoomNavView.selectedItemId = R.id.todo_list
                    //finish()
                }

                R.id.todo_list -> {
                    findNavController(R.id.fragment).navigate(R.id.listFragment)
                    //botoomNavView.selectedItemId = R.id.todo_list

                }

                //R.id.diary -> findNavController(R.id.fragment).navigate(R.id.updateFeagment)
            }
            true
        }

    }

    override fun onStart() {
        botoomNavView.selectedItemId = R.id.todo_list
        super.onStart()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }



}