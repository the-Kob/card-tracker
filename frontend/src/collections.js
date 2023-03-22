import sortBy from "sort-by";

const baseURL = 'http://localhost:8080/api/v1/collections';

export async function getCollections() {
    let collections;

    try {
        const response = await fetch(baseURL);
        collections = await response.json();
    } catch {
        collections = [];
    }


    return collections;
}

export async function createCollection() {
    /*
    await fetch(baseURL, {
        Method: 'POST',
        Headers: {
            Accept:
        }
    })

     */
}