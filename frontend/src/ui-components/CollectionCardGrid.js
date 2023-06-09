import CollectionCard from "./CollectionCard";
import React from "react";
import {Row} from "reactstrap";

export default function CollectionCardGrid({collections, series}) {
    return(
        <Row xs={1} md={4}>
            {collections.sort((a, b) => new Date(...a.releaseDate.split('/')) - new Date(...b.releaseDate.split('/'))).reverse().map((collection) => (
                <CollectionCard collection={collection} series={series}/>
            ))}
        </Row>
    );
}
