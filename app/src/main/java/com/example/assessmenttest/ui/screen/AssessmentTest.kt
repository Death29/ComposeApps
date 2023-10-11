package com.example.assessmenttest.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.assessmenttest.ui.theme.AssessmentTheme

@Composable
fun AssessmentTest(finishActivity: () -> Unit) {
    AssessmentTheme {
        val navController = rememberNavController()

        Scaffold { paddingValues ->
            com.example.assessmenttest.util.NavGraph(
                modifier = Modifier.padding(paddingValues),
                finishActivity,
                navController
            )
        }
    }
}