import React from "react";
import {getCollections} from "../collections";
import {useLoaderData} from "react-router-dom";
import CollectionCardGrid from "../ui-components/CollectionCardGrid";
import {Container} from "reactstrap";

export async function loader() {
    const collections = await getCollections();
    return { collections };
}

export default function Collections() {
    const { collections } = useLoaderData();

    return(
        <div>
            <Container fluid>
                <div>
                    {collections.length ? (
                        <CollectionCardGrid
                            collections={collections}
                        />
                    ) : (
                        <p>
                            <i>No collections</i>
                        </p>
                    )}
                </div>
            </Container>
        </div>
    );
}