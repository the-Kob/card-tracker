import React from "react";
import {
    Button,
    ButtonGroup,
    Card,
    CardBody,
    CardImg,
    CardSubtitle,
    CardTitle,
    Col,
    FormGroup,
    Input,
    Label
} from "reactstrap";
import {Link} from "react-router-dom";

export default function CollectionCard({collection}) {
    return(
        <Col>
            <Card key={collection.collectionId}>
                <CardBody className="text-center">
                    <Link to={collection.collectionId}>
                        <ButtonGroup style={{float: "right"}} size="small">
                            <Button color="primary">Edit</Button>
                        </ButtonGroup>
                    </Link>
                    <CardImg className=".card-img-top" top={true} src={collection.coverURL}/>
                    <CardTitle>{collection.name}</CardTitle>
                    <CardSubtitle className="mb-2 text-muted">{collection.series}</CardSubtitle>
                    <CardSubtitle className="mb-2 text-muted">{collection.releaseDate}</CardSubtitle>
                    <FormGroup>
                        <Input type="checkbox" readOnly={true} checked={collection.complete}/>
                        <Label>Complete</Label>
                    </FormGroup>
                    <Button color="primary" size="sm">OPEN</Button>
                </CardBody>
            </Card>
        </Col>
    );
}