const baseURL = 'http://localhost:8080/api/v1/collections';

const newCollection = {
    collectionId: 'new',
    name: '',
    series: '',
    releaseDate: '',
    complete: false,
    coverURL: 'https://i.ibb.co/zJ4Wsdc/Placeholder-view-vector-svg.png'
};

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

export async function getCollection(collectionId) {
    let collection;

    if(collectionId !== "new") {
        try {
            const response = await fetch(baseURL + "/" + collectionId);
            collection = await response.json();
        } catch {
            collection = newCollection;
        }
    } else {
        collection = newCollection;
    }

    return collection;
}

export async function createCollection(collection) {
    const request = new Request(baseURL, {
        method: "POST",
        body: JSON.stringify(collection),
        headers: new Headers({
            "Content-Type": "application/json"
        })
    });

    await fetch(request);
}