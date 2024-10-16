package com.example.demo_ui_exa_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient


class FragmentOption4 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_option4, container, false)

        val webView: WebView = view.findViewById(R.id.webView)

        // Habilitar JavaScript (opcional)
        webView.settings.javaScriptEnabled = true

        // Cargar una página web
        webView.loadUrl("https://fisc.utp.ac.pa/")

        // Configurar un WebViewClient para que las nuevas URL se carguen en el WebView
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url.toString())
                return true
            }
        }

        return view
    }

}