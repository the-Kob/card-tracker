import CollectionCard from "./CollectionCard";
import React from "react";
import {Row} from "reactstrap";

export default function CollectionCardGrid({collections}) {
    return(
        <Row xs={1} md={4}>
            {collections.map((collection) => (
                <CollectionCard
                    collection={collection}
                />
            ))}
        </Row>
    );
}
