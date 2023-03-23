import React from "react";
import {getCollections} from "../collections";
import {Link, useLoaderData} from "react-router-dom";
import CollectionCardGrid from "../ui-components/CollectionCardGrid";
import AppNavbar from "../ui-components/AppNavbar";
import {Button, Container} from "reactstrap";

export async function loader() {
    const collections = await getCollections();
    let series = [];

    collections.map(collection => {
        if(series.indexOf(collection.series) === -1) {
            series.push(collection.series);
        }
    })

    return { collections, series };
}

export default function Collections() {
    const { collections, series } = useLoaderData();

    return(
        <div>
            <AppNavbar />
            <Container fluid>
                <div className="float-right">
                    <Link to="/new" state={{series: series}}>
                        <Button color="success" >Add Collection</Button>
                    </Link>
                </div>
                <div>
                    {collections.length ? (
                        <CollectionCardGrid collections={collections} series={series}/>
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