package com.example.parksmart

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)



        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when (it.itemId){
                R.id.home_btn -> replacefragment(HomeFragment(),it.title.toString())
                R.id.maps_btn -> replacefragment(MapsFragment(),it.title.toString())

            }
            true
        }

        navView.menu.findItem(R.id.logout_btn).setOnMenuItemClickListener{item->
            when(item.itemId){
                R.id.logout_btn -> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, FirstActivity::class.java)
                    startActivity(intent)

                    true
                }
                else -> false
            }
        }

        navView.menu.findItem(R.id.pay_btn).setOnMenuItemClickListener{item->
        when(item.itemId){
            R.id.pay_btn -> {

                val intent = Intent(this, PaymentActivity::class.java)
                startActivity(intent)

                true
            }
            else -> false
            }
        }




    }


    private fun replacefragment(fragment: Fragment, title:String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}