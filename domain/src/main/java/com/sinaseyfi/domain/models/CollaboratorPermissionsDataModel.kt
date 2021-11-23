package com.sinaseyfi.domain.models

import com.sinaseyfi.domain.base.DataModel

data class CollaboratorPermissionsDataModel(
    val push: Boolean,
    val pull: Boolean,
    val admin: Boolean
): DataModel