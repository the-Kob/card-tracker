import {createCollection, getCollection} from "../collections";
import {redirect, useLoaderData, useLocation} from "react-router-dom";
import AppNavbar from "../ui-components/AppNavbar";
import {Container} from "reactstrap";
import CollectionForm from "../ui-components/CollectionForm";
import {getImageURL} from "../images";

export async function loader({params}) {
    const collection = await getCollection(params.collectionId);

    return { collection };
}

export async function action( {request, params} ) {
    const formData = await request.formData();
    const data = Object.fromEntries(formData);

    switch(request.method) {
        case "POST": {
            const collection = {
                name: data.name,
                series: data.series,
                releaseDate: data.releaseDate,
                complete: data.complete === "false" ? false : true,
                coverURL: data.coverURL
            }

            await createCollection(collection);

            return redirect('/');
        }
        case "PUT": {

        }
        default: {
            return redirect('/');
        }
    }
}

export default function Collection() {
    const { collection } = useLoaderData();
    const { state } = useLocation();
    const title = <h2>{collection.collectionId === "new" ? 'Add Collection' : 'Edit Collection'}</h2>;

    return(
      <div>
          <AppNavbar />
          {title}
          <Container fluid>
            <CollectionForm collection={collection} series={state.series}/>
          </Container>
      </div>
    );
}