document.addEventListener("DOMContentLoaded", () => {
    const API_URL = "http://localhost:8080/api/books";
    const bookForm = document.getElementById("bookForm");
    const booksTable = document.getElementById("booksTable").querySelector("tbody");

    bookForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        const bookId = document.getElementById("bookId").value;
        const title = document.getElementById("title").value;
        const author = document.getElementById("author").value;
        const isbn = document.getElementById("isbn").value;
        const price = document.getElementById("price").value;

        const book = { title, author, isbn, price: parseFloat(price) };

        if (bookId) {
            await updateBook(bookId, book);
        } else {
            await createBook(book);
        }

        bookForm.reset();
        fetchBooks();
    });

    async function fetchBooks() {
        const response = await fetch(API_URL);
        const books = await response.json();
        renderBooks(books);
    }

    async function createBook(book) {
        await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(book),
        });
    }

    async function updateBook(id, book) {
        await fetch(`${API_URL}/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(book),
        });
    }

    async function deleteBook(id) {
        await fetch(`${API_URL}/${id}`, {
            method: "DELETE",
        });
        fetchBooks();
    }

    function renderBooks(books) {
        booksTable.innerHTML = "";
        books.forEach((book) => {
            const tr = document.createElement("tr");

            tr.innerHTML = `
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.isbn}</td>
                <td>${book.price}</td>
                <td class="actions">
                    <button onclick="editBook(${book.id})">Edit</button>
                    <button onclick="deleteBook(${book.id})">Delete</button>
                </td>
            `;

            booksTable.appendChild(tr);
        });
    }

    window.editBook = async (id) => {
        const response = await fetch(`${API_URL}/${id}`);
        const book = await response.json();

        document.getElementById("bookId").value = book.id;
        document.getElementById("title").value = book.title;
        document.getElementById("author").value = book.author;
        document.getElementById("isbn").value = book.isbn;
        document.getElementById("price").value = book.price;
    };

    window.deleteBook = async (id) => {
        await deleteBook(id);
    };

    fetchBooks();
});
