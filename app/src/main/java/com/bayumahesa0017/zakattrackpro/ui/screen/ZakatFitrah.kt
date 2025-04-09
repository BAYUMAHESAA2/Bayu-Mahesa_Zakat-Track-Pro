    package com.bayumahesa0017.zakattrackpro.ui.screen

    import android.content.Intent
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.automirrored.filled.ArrowBack
    import androidx.compose.material.icons.outlined.Info
    import androidx.compose.material.icons.outlined.Share
    import androidx.compose.material3.Card
    import androidx.compose.material3.CardDefaults
    import androidx.compose.material3.DropdownMenuItem
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.ExposedDropdownMenuBox
    import androidx.compose.material3.ExposedDropdownMenuDefaults
    import androidx.compose.material3.ExtendedFloatingActionButton
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.OutlinedTextField
    import androidx.compose.material3.RadioButton
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.material3.TopAppBar
    import androidx.compose.material3.TopAppBarDefaults
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.res.stringResource
    import androidx.compose.ui.unit.dp
    import androidx.navigation.NavHostController
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableFloatStateOf
    import androidx.compose.runtime.mutableIntStateOf
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.platform.LocalContext
    import com.bayumahesa0017.zakattrackpro.R
    import com.bayumahesa0017.zakattrackpro.navigation.Screen

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ZakatFitrah(navController: NavHostController) {
        val context = LocalContext.current
        var jumlahOrang by remember { mutableIntStateOf(1) }
        val opsiOrang = (1..10).toList()
        var expanded by remember { mutableStateOf(false) }

        var typeZakat by remember { mutableStateOf("Beras") }

        val jumlahBeras by remember { mutableFloatStateOf(2.5f) }

        val jumlahUang by remember { mutableIntStateOf(47000) }

        val totalZakat = when (typeZakat) {
            "Beras" -> "${jumlahBeras * jumlahOrang} Kg Beras"
            else -> "Rp. ${jumlahUang * jumlahOrang}"
        }

        fun shareResult() {
            val sharePesan = """
                Hasil Perhitungan Zakat Fitrah:
                
                Jumlah Orang: $jumlahOrang
                Jenis Zakat: $typeZakat
                Total Zakat yang harus dibayar adalah: $totalZakat
            """.trimIndent()

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, sharePesan)
            }
            context.startActivity(Intent.createChooser(intent, "Bagikan Hasil Zakat Fitrah"))
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
                            text = stringResource(id = R.string.Zakatfitrah_screen)
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
                    },
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = { shareResult() },
                    icon = { Icon(Icons.Outlined.Share, contentDescription = "Bagikan") },
                    text = { Text("Bagikan") },
                    shape = RoundedCornerShape(16.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
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
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = jumlahOrang.toString(),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Jumlah Orang") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        opsiOrang.forEach { option ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        "$option Orang"
                                    )
                                },
                                onClick = {
                                    jumlahOrang = option
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Text(
                    text = "Jenis Zakat Fitrah",
                    style = MaterialTheme.typography.titleMedium
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = typeZakat == "Beras",
                            onClick = { typeZakat = "Beras" }
                        )
                        Text(
                            text = "Beras (2.5Kg)",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = typeZakat == "Uang",
                            onClick = { typeZakat = "Uang" }
                        )
                        Text(
                            text = "Uang (Rp. 47000)",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
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
                            text = "Total Zakat Fitrah"
                        )
                        Text(
                            text = totalZakat,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = "Untuk $jumlahOrang Orang"
                        )
                    }
                }
                Text(
                    text = stringResource(R.string.informasi)
                )
            }
        }
    }

