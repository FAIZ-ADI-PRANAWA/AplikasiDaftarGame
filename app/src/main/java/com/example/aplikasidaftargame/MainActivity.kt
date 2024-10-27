package com.example.aplikasidaftargame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.aplikasidaftargame.data.Game
import com.example.aplikasidaftargame.data.games
import com.example.aplikasidaftargame.ui.theme.AplikasiDaftarGameTheme

/**
 * Activity utama aplikasi.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplikasiDaftarGameTheme {
                // Menampilkan permukaan dengan warna latar belakang dari tema.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameApp() // Memanggil fungsi utama aplikasi.
                }
            }
        }
    }
}


/**
 * Fungsi utama yang menampilkan aplikasi game.
 */
@Composable
fun GameApp() {
    // Menampilkan Scaffold untuk layout dasar aplikasi.
    Scaffold(
        topBar = {
            GameTopAppBar() // Menampilkan TopAppBar.
        }
    ) { innerPadding ->
        // Menampilkan LazyColumn untuk daftar game.
        LazyColumn(contentPadding = innerPadding) {
            items(games) { game ->
                GameItem( // Menampilkan item game.
                    game = game, // Data game untuk item.
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)) // Padding item.
                )
            }
        }
    }
}


/**
 * Fungsi yang menampilkan item game.
 *
 * game Data game untuk item.
 * modifier Modifier untuk item.
 */
@Composable
fun GameItem(
    game: Game,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    // Menampilkan Card untuk item game.
    Card(
        modifier = modifier
    ) {
        // Menampilkan Column untuk konten item.
        Column(
            modifier = Modifier
                .animateContentSize( // Animasi untuk memperluas/menciutkan item.
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            // Menampilkan Row untuk ikon, informasi, dan tombol item.
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                GameIcon(game.imageResourceId) // Menampilkan ikon game.
                GameInformation(game.name, game.mb) // Menampilkan informasi game.
                Spacer(Modifier.weight(1f)) // Spacer untuk mendorong tombol ke kanan.
                GameItemButton(
                    // Menampilkan tombol item.
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            // Menampilkan hobi game jika item diperluas.
            if (expanded) {
                GameHobby(
                    game.hobbies, modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        bottom = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}


/**
 * Fungsi yang menampilkan tombol untuk memperluas/menciutkan item game.
 *
 * expanded State untuk ikon tombol (true jika diperluas, false jika diciutkan).
 * onClick Callback saat tombol diklik.
 * modifier Modifier untuk tombol.
 */
@Composable
private fun GameItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Menampilkan IconButton.
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        // Menampilkan ikon berdasarkan state expanded.
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}


/**
 * Fungsi yang menampilkan TopAppBar.
 *
 * modifier Modifier untuk TopAppBar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameTopAppBar(modifier: Modifier = Modifier) {
    // Menampilkan CenterAlignedTopAppBar.
    CenterAlignedTopAppBar(
        title = {
            // Menampilkan judul aplikasi.
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}


/**
 * Fungsi yang menampilkan ikon game.
 *
 * gameIcon Resource ID untuk gambar ikon game.
 * modifier Modifier untuk ikon game.
 */
@Composable
fun GameIcon(
    @DrawableRes gameIcon: Int,
    modifier: Modifier = Modifier
) {
    // Menampilkan Image.
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(gameIcon),
        contentDescription = null
    )
}


/**
 * Fungsi yang menampilkan informasi game (nama dan MB).
 *
 *  gameName Resource ID untuk string nama game.
 *  mb Ukuran game dalam MB.
 *  modifier Modifier untuk informasi game.
 */
@Composable
fun GameInformation(
    @StringRes gameName: Int,
    mb: Int,
    modifier: Modifier = Modifier
) {
    // Menampilkan Column untuk nama dan MB.
    Column(modifier = modifier) {
        Text(
            text = stringResource(gameName),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.MB, mb),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Composable
fun GameHobby(
    @StringRes gameHobby: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(gameHobby),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameAppPreview() {
    AplikasiDaftarGameTheme {
        GameApp()
    }
}



@Preview(showBackground = true)
@Composable
fun GameAppDarkThemePreview() {
    AplikasiDaftarGameTheme {
        GameApp()
    }
}