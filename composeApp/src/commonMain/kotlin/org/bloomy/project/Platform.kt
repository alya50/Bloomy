
    package org.bloomy.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform