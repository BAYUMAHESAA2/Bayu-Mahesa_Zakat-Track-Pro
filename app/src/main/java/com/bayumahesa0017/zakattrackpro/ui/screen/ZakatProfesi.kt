package com.bayumahesa0017.zakattrackpro.ui.screen

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bayumahesa0017.zakattrackpro.R
import com.bayumahesa0017.zakattrackpro.navigation.Screen
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZakatProfesi(navController: NavHostController) {
    val context = LocalContext.current

    var pemasukanPerbulan by rememberSaveable { mutableStateOf("") }
    var bonus by rememberSaveable { mutableStateOf("") }
    var totalZakat by rememberSaveable { mutableDoubleStateOf(0.0) }
    var lihatError by rememberSaveable { mutableStateOf(false) }

    val decimalFormat = remember {
        val formatSymbols = DecimalFormatSymbols(Locale("id", "ID"))
        formatSymbols.groupingSeparator = '.'
        DecimalFormat("#,###", formatSymbols).apply {
            maximumFractionDigits = 0
        }
    }

    fun formatRupiah(amount: Double): String {
        return "Rp. ${decimalFormat.format(amount)}"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = Color.White
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.ZakatProfesi_screen)
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.DarkGray,
                    titleContentColor = MaterialTheme.colorScheme.errorContainer
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.AboutScreen.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.about_screen),
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = pemasukanPerbulan,
                onValueChange = { newValue ->
                    pemasukanPerbulan = newValue.filter { it.isDigit() }
                    lihatError = false
                },
                label = { Text(stringResource(R.string.pendPerbulan)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = lihatError && pemasukanPerbulan.isEmpty(),
                supportingText = {
                    if (lihatError && pemasukanPerbulan.isEmpty()) {
                        Text(stringResource(R.string.errorpend))
                    }
                }
            )

            OutlinedTextField(
                value = bonus,
                onValueChange = { newValue ->
                    bonus = newValue.filter { it.isDigit() }
                    lihatError = false
                },
                label = { Text(stringResource(R.string.bonusthr)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (pemasukanPerbulan.isEmpty()) {
                        lihatError = true
                        return@Button
                    }

                    val income = pemasukanPerbulan.toDoubleOrNull() ?: 0.0
                    val bonusValue = bonus.toDoubleOrNull() ?: 0.0
                    val totalIncome = income + bonusValue


                    totalZakat = totalIncome * 0.025
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                )
            ) {
                Text(stringResource(R.string.hitungzakat))
            }

            if (totalZakat > 0) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.Black
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.jumlahzakat),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = formatRupiah(totalZakat),
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Spacer(modifier = Modifier.size(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    pemasukanPerbulan = ""
                                    bonus = ""
                                    totalZakat = 0.0
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            ) {
                                Text("Reset")
                            }

                            Button(
                                onClick = {
                                    shareResult(
                                        context = context,
                                        pendapatanPerbulan = pemasukanPerbulan,
                                        bonus = bonus,
                                        totalZakat = totalZakat,
                                        formatter = decimalFormat
                                    )
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Black
                                )
                            ) {
                                Text(stringResource(R.string.bagikan))
                            }
                        }
                    }
                }
            }
        }
    }
}
fun shareResult(
    context: Context,
    pendapatanPerbulan: String,
    bonus: String,
    totalZakat: Double,
    formatter: DecimalFormat
) {
    val formattedPendapatan = formatter.format(pendapatanPerbulan.toDoubleOrNull() ?: 0.0)
    val formattedBonus = formatter.format(bonus.toDoubleOrNull() ?: 0.0)
    val formattedZakat = formatter.format(totalZakat)

    val message = context.getString(
        R.string.bagikan_hasi_Profesi,
        formattedPendapatan,
        formattedBonus,
        formattedZakat
    )

    val share = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }

    if (share.resolveActivity(context.packageManager) != null) {
        context.startActivity(share)
    }
}