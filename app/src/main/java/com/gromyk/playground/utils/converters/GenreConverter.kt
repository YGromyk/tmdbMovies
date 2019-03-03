package com.gromyk.playground.utils.converters

import com.gromyk.persistence.model.genres.DBGenre
import com.gromyk.playground.api.dtos.genres.GenreDTO

/**
 * Created by Yurii Gromyk on 1/19/19.
 */

fun GenreDTO.toDBGenre() = DBGenre(id, name)

fun DBGenre.toGenreDtO() = GenreDTO(id, name)