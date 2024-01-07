package com.example.importantexample.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import com.example.importantexample.R

class WebviewActivity : AppCompatActivity() {

    lateinit var webview: WebView
    lateinit var pBar: ProgressBar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        webview = findViewById(R.id.webview)
        pBar = findViewById(R.id.pBar)

        //webview.settings.setJavaScriptEnabled(true)

        webview.loadUrl("https://www.google.com/")

        //problem? dusari website bhi hamara app mai chalna chahya
        //means new web request call
        webview.webViewClient = object : WebViewClient() {
            /*override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }*/
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                pBar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                pBar.visibility = View.GONE
                super.onPageFinished(view, url)
            }
        }

        //Backpress on webview(Stack)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { //Alternative
                if (webview.canGoBack()){
                    webview.goBack()
                }else{
                    finish()
                }
            }
        }
        onBackPressedDispatcher.addCallback(onBackPressedCallback)

    }

    /*override fun onBackPressed() { //deprecated Api: 33 onwards
        if (webview.canGoBack()){
            webview.goBack()
        }else{
            super.onBackPressed()
        }
    }*/

}