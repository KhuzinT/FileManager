package com.example.filemanager.screens.utils

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filemanager.R

enum class SortedBy(@StringRes val title: Int) {
    NameAZ(title = R.string.sorted_by_name_az),
    NameZA(title = R.string.sorted_by_name_za),
    SizeIncrease(title = R.string.sorted_by_size_increase),
    SizeDecrease(title = R.string.sorted_by_size_decrease),
    DateIncrease(title = R.string.sorted_by_date_increase),
    DateDecrease(title = R.string.sorted_by_date_decrease),
    Extension(title = R.string.sorted_by_extension);

}

private fun getValue(@StringRes title: Int): SortedBy {
    return SortedBy.values().filter { it.title == title }[0]
}

@Composable
fun FileSorter(
    sortedBy: SortedBy,
    updateSort: (SortedBy) -> Unit,
    modifier: Modifier = Modifier
) {
    val isMenuOpen = rememberSaveable {
        mutableStateOf(false)
    }

    Box() {
        IconButton(onClick = { isMenuOpen.value = true }, modifier = modifier) {
            Icon(
                imageVector = Icons.Outlined.Menu,
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground,
                modifier = modifier.size(40.dp, 40.dp)
            )
        }
        DropdownMenu(
            expanded = isMenuOpen.value,
            onDismissRequest = { isMenuOpen.value = false },
            modifier = modifier
                .background(MaterialTheme.colors.background)
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(10.dp)
                )
                .widthIn(max = 300.dp)
        ) {
            FileSorterItem(
                onClick = { updateSort(getValue(R.string.sorted_by_name_az)) },
                titleId = R.string.sorted_by_name_az,
                selectedId = sortedBy.title
            )

            FileSorterItem(
                onClick = { updateSort(getValue(R.string.sorted_by_name_za)) },
                titleId = R.string.sorted_by_name_za,
                selectedId = sortedBy.title
            )

            FileSorterItem(
                onClick = { updateSort(getValue(R.string.sorted_by_size_increase)) },
                titleId = R.string.sorted_by_size_increase,
                selectedId = sortedBy.title
            )

            FileSorterItem(
                onClick = { updateSort(getValue(R.string.sorted_by_size_decrease)) },
                titleId = R.string.sorted_by_size_decrease,
                selectedId = sortedBy.title
            )

            FileSorterItem(
                onClick = { updateSort(getValue(R.string.sorted_by_date_increase)) },
                titleId = R.string.sorted_by_date_increase,
                selectedId = sortedBy.title
            )

            FileSorterItem(
                onClick = { updateSort(getValue(R.string.sorted_by_date_decrease)) },
                titleId = R.string.sorted_by_date_decrease,
                selectedId = sortedBy.title
            )

            FileSorterItem(
                onClick = { updateSort(getValue(R.string.sorted_by_extension)) },
                titleId = R.string.sorted_by_extension,
                selectedId = sortedBy.title
            )
        }
    }
}

@Composable
fun FileSorterItem(
    onClick: () -> Unit,
    @StringRes titleId: Int,
    @StringRes selectedId: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(
                if (titleId == selectedId) {
                    MaterialTheme.colors.surface.copy(0.6f)
                } else {
                    MaterialTheme.colors.background.copy(alpha = 0.6f)
                }
            )
    ) {
        Text(
            text = stringResource(id = titleId),
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight(300),
            fontSize = 18.sp,
            modifier = modifier
                .padding(10.dp)
        )
    }
}