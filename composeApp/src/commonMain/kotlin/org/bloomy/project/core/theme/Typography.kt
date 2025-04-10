package org.bloomy.project.core.theme


import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import bloomy.composeapp.generated.resources.Res
import bloomy.composeapp.generated.resources.geist_black
import bloomy.composeapp.generated.resources.geist_bold
import bloomy.composeapp.generated.resources.geist_extrabold
import bloomy.composeapp.generated.resources.geist_extralight
import bloomy.composeapp.generated.resources.geist_light
import bloomy.composeapp.generated.resources.geist_medium
import bloomy.composeapp.generated.resources.geist_regular
import bloomy.composeapp.generated.resources.geist_semibold
import bloomy.composeapp.generated.resources.geist_thin
import bloomy.composeapp.generated.resources.inter_lg_black
import bloomy.composeapp.generated.resources.inter_lg_blackitalic
import bloomy.composeapp.generated.resources.inter_lg_bold
import bloomy.composeapp.generated.resources.inter_lg_bolditalic
import bloomy.composeapp.generated.resources.inter_lg_extrabold
import bloomy.composeapp.generated.resources.inter_lg_extrabolditalic
import bloomy.composeapp.generated.resources.inter_lg_extralight
import bloomy.composeapp.generated.resources.inter_lg_extralightitalic
import bloomy.composeapp.generated.resources.inter_lg_italic
import bloomy.composeapp.generated.resources.inter_lg_light
import bloomy.composeapp.generated.resources.inter_lg_lightitalic
import bloomy.composeapp.generated.resources.inter_lg_medium
import bloomy.composeapp.generated.resources.inter_lg_mediumitalic
import bloomy.composeapp.generated.resources.inter_lg_regular
import bloomy.composeapp.generated.resources.inter_lg_semibold
import bloomy.composeapp.generated.resources.inter_lg_semibolditalic
import bloomy.composeapp.generated.resources.inter_lg_thin
import bloomy.composeapp.generated.resources.inter_lg_thinitalic
import bloomy.composeapp.generated.resources.inter_md_black
import bloomy.composeapp.generated.resources.inter_md_blackitalic
import bloomy.composeapp.generated.resources.inter_md_bold
import bloomy.composeapp.generated.resources.inter_md_bolditalic
import bloomy.composeapp.generated.resources.inter_md_extrabold
import bloomy.composeapp.generated.resources.inter_md_extrabolditalic
import bloomy.composeapp.generated.resources.inter_md_extralight
import bloomy.composeapp.generated.resources.inter_md_extralightitalic
import bloomy.composeapp.generated.resources.inter_md_italic
import bloomy.composeapp.generated.resources.inter_md_light
import bloomy.composeapp.generated.resources.inter_md_lightitalic
import bloomy.composeapp.generated.resources.inter_md_medium
import bloomy.composeapp.generated.resources.inter_md_mediumitalic
import bloomy.composeapp.generated.resources.inter_md_regular
import bloomy.composeapp.generated.resources.inter_md_semibold
import bloomy.composeapp.generated.resources.inter_md_thin
import bloomy.composeapp.generated.resources.inter_md_thinitalic
import bloomy.composeapp.generated.resources.inter_sm_black
import bloomy.composeapp.generated.resources.inter_sm_blackitalic
import bloomy.composeapp.generated.resources.inter_sm_bold
import bloomy.composeapp.generated.resources.inter_sm_bolditalic
import bloomy.composeapp.generated.resources.inter_sm_extrabold
import bloomy.composeapp.generated.resources.inter_sm_extrabolditalic
import bloomy.composeapp.generated.resources.inter_sm_extralight
import bloomy.composeapp.generated.resources.inter_sm_extralightitalic
import bloomy.composeapp.generated.resources.inter_sm_italic
import bloomy.composeapp.generated.resources.inter_sm_light
import bloomy.composeapp.generated.resources.inter_sm_lightitalic
import bloomy.composeapp.generated.resources.inter_sm_medium
import bloomy.composeapp.generated.resources.inter_sm_mediumitalic
import bloomy.composeapp.generated.resources.inter_sm_regular
import bloomy.composeapp.generated.resources.inter_sm_semibold
import bloomy.composeapp.generated.resources.inter_sm_thin
import bloomy.composeapp.generated.resources.inter_sm_thinitalic
import bloomy.composeapp.generated.resources.outfit_black
import bloomy.composeapp.generated.resources.outfit_bold
import bloomy.composeapp.generated.resources.outfit_extrabold
import bloomy.composeapp.generated.resources.outfit_extralight
import bloomy.composeapp.generated.resources.outfit_light
import bloomy.composeapp.generated.resources.outfit_medium
import bloomy.composeapp.generated.resources.outfit_regular
import bloomy.composeapp.generated.resources.outfit_semibold
import bloomy.composeapp.generated.resources.outfit_thin
import org.jetbrains.compose.resources.Font

@Composable
fun InterSmItalic(): FontFamily = FontFamily(
    Font(Res.font.inter_sm_blackitalic, FontWeight.Black),
    Font(Res.font.inter_sm_extrabolditalic, FontWeight.ExtraBold),
    Font(Res.font.inter_sm_extralightitalic, FontWeight.ExtraLight),
    Font(Res.font.inter_sm_thinitalic, FontWeight.Thin),
    Font(Res.font.inter_sm_bolditalic, FontWeight.Bold),
    Font(Res.font.inter_sm_italic, FontWeight.Normal),
    Font(Res.font.inter_sm_lightitalic, FontWeight.Light),
    Font(Res.font.inter_sm_mediumitalic, FontWeight.Medium),
    Font(Res.font.inter_sm_semibold, FontWeight.SemiBold),
)

@Composable
fun InterSm(): FontFamily = FontFamily(
    Font(Res.font.inter_sm_black, FontWeight.Black),
    Font(Res.font.inter_sm_extrabold, FontWeight.ExtraBold),
    Font(Res.font.inter_sm_extralight, FontWeight.ExtraLight),
    Font(Res.font.inter_sm_thin, FontWeight.Thin),
    Font(Res.font.inter_sm_bold, FontWeight.Bold),
    Font(Res.font.inter_sm_regular, FontWeight.Normal),
    Font(Res.font.inter_sm_light, FontWeight.Light),
    Font(Res.font.inter_sm_medium, FontWeight.Medium),
    Font(Res.font.inter_sm_semibold, FontWeight.SemiBold),
)

@Composable
fun InterMdItalic(): FontFamily = FontFamily(
    Font(Res.font.inter_md_blackitalic, FontWeight.Black),
    Font(Res.font.inter_md_extrabolditalic, FontWeight.ExtraBold),
    Font(Res.font.inter_md_extralightitalic, FontWeight.ExtraLight),
    Font(Res.font.inter_md_thinitalic, FontWeight.Thin),
    Font(Res.font.inter_md_bolditalic, FontWeight.Bold),
    Font(Res.font.inter_md_italic, FontWeight.Normal),
    Font(Res.font.inter_md_lightitalic, FontWeight.Light),
    Font(Res.font.inter_md_mediumitalic, FontWeight.Medium),
    Font(Res.font.inter_md_semibold, FontWeight.SemiBold),
)

@Composable
fun InterMd(): FontFamily = FontFamily(
    Font(Res.font.inter_md_black, FontWeight.Black),
    Font(Res.font.inter_md_extrabold, FontWeight.ExtraBold),
    Font(Res.font.inter_md_extralight, FontWeight.ExtraLight),
    Font(Res.font.inter_md_thin, FontWeight.Thin),
    Font(Res.font.inter_md_bold, FontWeight.Bold),
    Font(Res.font.inter_md_regular, FontWeight.Normal),
    Font(Res.font.inter_md_light, FontWeight.Light),
    Font(Res.font.inter_md_medium, FontWeight.Medium),
    Font(Res.font.inter_md_semibold, FontWeight.SemiBold),
)

@Composable
fun InterLgItalic(): FontFamily = FontFamily(
    Font(Res.font.inter_lg_blackitalic, FontWeight.Black),
    Font(Res.font.inter_lg_extrabolditalic, FontWeight.ExtraBold),
    Font(Res.font.inter_lg_extralightitalic, FontWeight.ExtraLight),
    Font(Res.font.inter_lg_thinitalic, FontWeight.Thin),
    Font(Res.font.inter_lg_bolditalic, FontWeight.Bold),
    Font(Res.font.inter_lg_italic, FontWeight.Normal),
    Font(Res.font.inter_lg_lightitalic, FontWeight.Light),
    Font(Res.font.inter_lg_mediumitalic, FontWeight.Medium),
    Font(Res.font.inter_lg_semibolditalic, FontWeight.SemiBold),
)

@Composable
fun InterLg(): FontFamily = FontFamily(
    Font(Res.font.inter_lg_black, FontWeight.Black),
    Font(Res.font.inter_lg_extrabold, FontWeight.ExtraBold),
    Font(Res.font.inter_lg_extralight, FontWeight.ExtraLight),
    Font(Res.font.inter_lg_thin, FontWeight.Thin),
    Font(Res.font.inter_lg_bold, FontWeight.Bold),
    Font(Res.font.inter_lg_regular, FontWeight.Normal),
    Font(Res.font.inter_lg_light, FontWeight.Light),
    Font(Res.font.inter_lg_medium, FontWeight.Medium),
    Font(Res.font.inter_lg_semibold, FontWeight.SemiBold),
)

@Composable
fun OutfitFontFamily(): FontFamily = FontFamily(
    Font(Res.font.outfit_black, FontWeight.Black),
    Font(Res.font.outfit_extrabold, FontWeight.ExtraBold),
    Font(Res.font.outfit_extralight, FontWeight.ExtraLight),
    Font(Res.font.outfit_thin, FontWeight.Thin),
    Font(Res.font.outfit_bold, FontWeight.Bold),
    Font(Res.font.outfit_regular, FontWeight.Normal),
    Font(Res.font.outfit_light, FontWeight.Light),
    Font(Res.font.outfit_medium, FontWeight.Medium),
    Font(Res.font.outfit_semibold, FontWeight.SemiBold),
)

@Composable
fun GeistFontFamily(): FontFamily = FontFamily(
    Font(Res.font.geist_black, FontWeight.Black),
    Font(Res.font.geist_extrabold, FontWeight.ExtraBold),
    Font(Res.font.geist_extralight, FontWeight.ExtraLight),
    Font(Res.font.geist_thin, FontWeight.Thin),
    Font(Res.font.geist_bold, FontWeight.Bold),
    Font(Res.font.geist_regular, FontWeight.Normal),
    Font(Res.font.geist_light, FontWeight.Light),
    Font(Res.font.geist_medium, FontWeight.Medium),
    Font(Res.font.geist_semibold, FontWeight.SemiBold),
)

@Composable
fun Typography() = Typography().run {
    val outfitFontFamily = OutfitFontFamily()

    copy(
        displayLarge = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp
        ),
        displayMedium = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp
        ),
        displaySmall = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),
        titleLarge = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        titleSmall = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),
        labelLarge = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    )
}