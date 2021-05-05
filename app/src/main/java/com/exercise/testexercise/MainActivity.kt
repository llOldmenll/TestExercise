package com.exercise.testexercise

import android.view.ViewGroup
import com.exercise.testexercise.ribs.root.RootBuilder
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class MainActivity : RibActivity() {
    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> =
        RootBuilder(object : RootBuilder.ParentComponent {})
            .build(parentViewGroup, this.applicationContext)
}