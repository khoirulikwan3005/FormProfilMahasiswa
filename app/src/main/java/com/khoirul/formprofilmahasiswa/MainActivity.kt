package com.khoirul.formprofilmahasiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.khoirul.formprofilmahasiswa.ui.theme.FormProfilMahasiswaTheme

private val Blue = Color(0xFF315BE8)
private val Navy = Color(0xFF172554)
private val Background = Color(0xFFF3F6FC)
private val DarkText = Color(0xFF17233D)
private val MutedText = Color(0xFF778198)
private val BorderColor = Color(0xFFE2E8F3)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FormProfilMahasiswaTheme {
                StudentProfileApp()
            }
        }
    }
}

@Composable
fun StudentProfileApp() {
    // STATE: menyimpan input dan kondisi halaman
    var nama by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var prodi by remember { mutableStateOf("Informatika") }
    var semester by remember { mutableStateOf("Semester 5") }

    var showProfile by remember { mutableStateOf(false) }
    var prodiExpanded by remember { mutableStateOf(false) }
    var semesterExpanded by remember { mutableStateOf(false) }

    val prodiList = listOf(
        "Informatika",
        "Sistem Informasi",
        "Manajemen",
        "Akuntansi"
    )

    val semesterList = (1..8).map { "Semester $it" }

    // Tombol kembali HP mengembalikan pengguna ke formulir
    BackHandler(enabled = showProfile) {
        showProfile = false
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        if (showProfile) {
            // HALAMAN KEDUA: HASIL PROFIL
            ProfileScreen(
                nama = nama,
                nim = nim,
                prodi = prodi,
                semester = semester,
                onBack = {
                    showProfile = false
                }
            )
        } else {
            // HALAMAN PERTAMA: FORMULIR
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                // HEADER
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Navy
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(50.dp),
                            color = Color.White.copy(alpha = 0.12f)
                        ) {
                            Text(
                                text = "✦  STUDENT PORTAL",
                                modifier = Modifier.padding(
                                    horizontal = 12.dp,
                                    vertical = 7.dp
                                ),
                                color = Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.2.sp
                            )
                        }

                        Text(
                            text = "Profil Mahasiswa",
                            color = Color.White,
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Lengkapi identitas akademikmu " +
                                    "dengan data yang benar.",
                            color = Color.White.copy(alpha = 0.78f),
                            fontSize = 14.sp,
                            lineHeight = 21.sp
                        )

                        Surface(
                            shape = RoundedCornerShape(50.dp),
                            color = Color(0xFF24436D)
                        ) {
                            Text(
                                text = "FORM DATA DIRI",
                                modifier = Modifier.padding(
                                    horizontal = 11.dp,
                                    vertical = 6.dp
                                ),
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                // FORM DATA MAHASISWA
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 1.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFFEBF0FF)
                            ) {
                                Text(
                                    text = "01",
                                    modifier = Modifier.padding(
                                        horizontal = 12.dp,
                                        vertical = 10.dp
                                    ),
                                    color = Blue,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Column(
                                verticalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                                Text(
                                    text = "Informasi Akademik",
                                    color = DarkText,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "Isi semua data yang diperlukan",
                                    color = MutedText,
                                    fontSize = 12.sp
                                )
                            }
                        }

                        HorizontalDivider(color = BorderColor)

                        // INPUT NAMA
                        FormLabel("Nama lengkap")

                        OutlinedTextField(
                            value = nama,
                            onValueChange = {
                                nama = it
                            },
                            placeholder = {
                                Text(
                                    "Contoh: Khoirul Ikwan",
                                    color = MutedText
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            shape = RoundedCornerShape(14.dp)
                        )

                        // INPUT NIM
                        FormLabel("Nomor Induk Mahasiswa")

                        OutlinedTextField(
                            value = nim,
                            onValueChange = {
                                nim = it
                            },
                            placeholder = {
                                Text(
                                    "Masukkan NIM",
                                    color = MutedText
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            shape = RoundedCornerShape(14.dp)
                        )

                        // DROPDOWN PROGRAM STUDI
                        FormLabel("Program studi")

                        Box(modifier = Modifier.fillMaxWidth()) {
                            OutlinedTextField(
                                value = prodi,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    Text(
                                        text = if (prodiExpanded) "▲" else "▼",
                                        color = Blue,
                                        fontSize = 12.sp
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp)
                            )

                            // Area klik transparan di atas field
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clickable {
                                        prodiExpanded = !prodiExpanded
                                        semesterExpanded = false
                                    }
                            )

                            DropdownMenu(
                                expanded = prodiExpanded,
                                onDismissRequest = {
                                    prodiExpanded = false
                                }
                            ) {
                                prodiList.forEach { pilihan ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = pilihan,
                                                fontSize = 14.sp
                                            )
                                        },
                                        onClick = {
                                            prodi = pilihan
                                            prodiExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        // DROPDOWN SEMESTER
                        FormLabel("Semester")

                        Box(modifier = Modifier.fillMaxWidth()) {
                            OutlinedTextField(
                                value = semester,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    Text(
                                        text = if (semesterExpanded) "▲" else "▼",
                                        color = Blue,
                                        fontSize = 12.sp
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp)
                            )

                            // Area klik transparan di atas field
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clickable {
                                        semesterExpanded = !semesterExpanded
                                        prodiExpanded = false
                                    }
                            )

                            DropdownMenu(
                                expanded = semesterExpanded,
                                onDismissRequest = {
                                    semesterExpanded = false
                                }
                            ) {
                                semesterList.forEach { pilihan ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = pilihan,
                                                fontSize = 14.sp
                                            )
                                        },
                                        onClick = {
                                            semester = pilihan
                                            semesterExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        // EVENT: TOMBOL MENAMPILKAN PROFIL
                        Button(
                            onClick = {
                                prodiExpanded = false
                                semesterExpanded = false
                                showProfile = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),
                            enabled = nama.isNotBlank() &&
                                    nim.isNotBlank(),
                            shape = RoundedCornerShape(15.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Blue,
                                disabledContainerColor = Color(0xFFB9C6E8)
                            )
                        ) {
                            Text(
                                text = "Tampilkan Profil  →",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Text(
                            text = "Pastikan nama dan NIM sudah benar " +
                                    "sebelum menampilkan profil.",
                            color = MutedText,
                            fontSize = 12.sp,
                            lineHeight = 18.sp
                        )
                    }
                }

                // FOOTER
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "FORM PROFIL MAHASISWA",
                        color = MutedText,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )

                    Text(
                        text = "Dibuat dengan Kotlin & Jetpack Compose",
                        color = MutedText,
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

// HALAMAN PROFIL TERPISAH
@Composable
fun ProfileScreen(
    nama: String,
    nim: String,
    prodi: String,
    semester: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        // Tombol kembali
        Surface(
            modifier = Modifier
                .clickable { onBack() },
            shape = RoundedCornerShape(14.dp),
            color = Color.White
        ) {
            Text(
                text = "←  Kembali ke Formulir",
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ),
                color = Blue,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        // HEADER PROFIL
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(
                containerColor = Navy
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(50.dp),
                    color = Color.White.copy(alpha = 0.12f)
                ) {
                    Text(
                        text = "✓  PROFIL TERSIMPAN",
                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 7.dp
                        ),
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                Text(
                    text = "Profil Mahasiswa",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )

                Text(
                    text = nama,
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 35.sp
                )

                Text(
                    text = "Mahasiswa aktif",
                    color = Color.White.copy(alpha = 0.78f),
                    fontSize = 14.sp
                )
            }
        }

        // DETAIL AKADEMIK
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Text(
                    text = "Detail Akademik",
                    color = DarkText,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                HorizontalDivider(color = BorderColor)

                ProfileRow("NIM", nim)

                HorizontalDivider(color = BorderColor)

                ProfileRow("Program Studi", prodi)

                HorizontalDivider(color = BorderColor)

                ProfileRow("Semester", semester)
            }
        }

        // INFORMASI TAMBAHAN
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            color = Color(0xFFEBF0FF)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Data Profil",
                    color = Blue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Informasi ini ditampilkan berdasarkan " +
                            "data yang kamu masukkan pada formulir.",
                    color = DarkText,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
            }
        }

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            )
        ) {
            Text(
                text = "Edit Data Profil",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "FORM PROFIL MAHASISWA",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            color = MutedText,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.2.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FormLabel(label: String) {
    Text(
        text = label,
        color = DarkText,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun ProfileRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = MutedText,
            fontSize = 13.sp,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = value,
            color = DarkText,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1.5f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StudentProfilePreview() {
    FormProfilMahasiswaTheme {
        StudentProfileApp()
    }
}