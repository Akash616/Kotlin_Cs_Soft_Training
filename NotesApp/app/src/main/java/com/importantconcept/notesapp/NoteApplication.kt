package com.importantconcept.notesapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp  //pura application ka andar sirf ak hi object hoga(here reftrofit object - singleton)
class NoteApplication : Application() { //Application class - entry point



}