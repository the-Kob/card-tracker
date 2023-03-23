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

export default function CollectionCard({collection, series}) {
    const c = collection;

    return(
        <Col>
            <Card key={c.collectionId}>
                <CardBody className="text-center">
                    <ButtonGroup style={{float: "right"}} size="small">
                        <Link to={`${c.collectionId}`} state={{series: series}}>
                            <Button color="primary">edit</Button>
                        </Link>
                        <Button color="danger">delete</Button>
                    </ButtonGroup>
                    <CardImg className=".card-img-top" top={true} src={c.coverURL}/>
                    <CardTitle>{c.name}</CardTitle>
                    <CardSubtitle className="mb-2 text-muted">{c.series}</CardSubtitle>
                    <CardSubtitle className="mb-2 text-muted">{c.releaseDate}</CardSubtitle>
                    <FormGroup>
                        <Input type="checkbox" readOnly={true} checked={c.complete}/>{' '}
                        <Label>Complete</Label>
                    </FormGroup>
                    <Button color="primary" size="sm">OPEN</Button>
                </CardBody>
            </Card>
        </Col>
    );
}