package app.example.core.domain

import app.example.graphql.GetAllBooksQuery
import app.example.graphql.GetBookByUuidQuery
import app.example.graphql.InsertBookMutation
import app.example.graphql.fragment.BookFragment

fun BookResponse.toEntity(): BookEntity {
    return BookEntity(uuid, author, title, created)
}

fun BookFragment.toResponse(): BookResponse {
    return BookResponse(uuid, title, author, created)
}

fun GetBookByUuidQuery.Data.toResponse(): BookResponse {
    return getBookByUuid.bookFragment.toResponse()
}

fun GetAllBooksQuery.Data.toResponse(): List<BookResponse> {
    return getAllBooks.map { it.bookFragment.toResponse() }
}

fun InsertBookMutation.Data.toResponse(): BookResponse {
    return insertBook.bookFragment.toResponse()
}
