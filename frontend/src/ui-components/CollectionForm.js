import {Form, Link} from "react-router-dom";
import {Button, FormGroup, FormText, Input, Label} from "reactstrap";
import {useState} from "react";
import CreatableSelect from "react-select/creatable";
import {getImageURL} from "../images";

function processOptions(series) {
    let options = [];

    series.forEach(element => {
        options.push(createOption(element))
    });

    return options;
}

function createOption(series) {
    let option = {
        label: series.toUpperCase().replace(/\W/g, ''),
        value: series.toUpperCase().replace(/\W/g, '')
    }

    return option;
}

export default function CollectionForm({ collection, series }) {
    const [c, setC] = useState(collection);
    const [options, setOptions] = useState(processOptions(series));
    const [selectedOption, setSelectedOption] = useState(createOption(c.series))
    const [creatableLoading, setCreatableLoading] = useState(false);
    const [file, setFile] = useState(null);
    const [uploadingImage, setUploadingImage] = useState(false);

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setC((prevState) => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSeriesChange = (option) => {
        setSelectedOption(option);

        if(option == null) { // If no option is selected (nullable)
            setC((prevState) => ({
                ...prevState,
                series: ''
            }));
        } else {
            setC((prevState) => ({
                ...prevState,
                series: option.value
            }));
        }
    }

    const handleCreateOption = (event) => {
        setCreatableLoading(true);
        setTimeout(() => {
            // Create new option and update existing ones
            const tempOptions = options;
            const newOption = createOption(event);
            tempOptions.push(newOption)
            setCreatableLoading(false);
            setOptions(tempOptions);

            // Change the newly created option to be te chosen one
            handleSeriesChange(newOption)
        }, 1000);
    }

    const handleCoverChange = (event) => {
        setUploadingImage(true);
        setTimeout(async () => {
            const file = event.target.files[0];
            setFile(file);

            const url = await getImageURL(file);
            console.log(url)
            setC((prevState) => ({
                ...prevState,
                coverURL: url
            }));
            setUploadingImage(false)
        }, 1000);

    }

    const handleCompleteChange = (event) => {
        const name = event.target.name;
        const value = event.target.checked;
        setC((prevState) => ({
            ...prevState,
            [name]: value
        }));
    };

    return(
        <Form method={c.collectionId === "new" ? "post" : "put"}>
            <FormGroup>
                <Label for="name">Name</Label>
                <Input type="text"
                       id="name"
                       name="name"
                       required={true}
                       value={c.name}
                       onChange={handleInputChange}
                />
            </FormGroup>
            <FormGroup>
                <Label for="releaseDate">Release Date</Label>
                <Input type="date"
                       id="releaseDate"
                       name="releaseDate"
                       required={true}
                       value={c.releaseDate}
                       onChange={handleInputChange}
                />
            </FormGroup>
            <FormGroup>
                <Label for="series">Series</Label>
                <CreatableSelect id="series"
                                 name="series"
                                 isClearable={true}
                                 required={true}
                                 isDisabled={creatableLoading}
                                 isLoading={creatableLoading}
                                 closeMenuOnSelect={true}
                                 onCreateOption={handleCreateOption}
                                 options={options}
                                 value={selectedOption}
                                 onChange={(value) => handleSeriesChange(value)}
                />
            </FormGroup>
            <FormText>
                You can create your own series by just writing it.
            </FormText>
            <FormGroup>
                <Label for="complete">Complete</Label>{' '}
                <Input type="checkbox"
                       id="complete"
                       name="complete"
                       checked={c.complete}
                       onChange={handleCompleteChange}
                />
            </FormGroup>
            <FormGroup>
                <Label for="file">Cover Image</Label>
                <Input type="file"
                       id="file"
                       name="file"
                       accept="image/*"
                       label={file || "choose a cover image"}
                       onChange={handleCoverChange}
                />
            </FormGroup>
            <FormGroup>
                <Input type="hidden" name="complete" value={c.complete} />
                <Input type="hidden" name="coverURL" value={c.coverURL} />
            </FormGroup>
            <FormGroup>
                <Button color="primary" type="submit" disabled={uploadingImage}>Save</Button>{' '}
                <Link to="/">
                    <Button color="danger">Cancel</Button>
                </Link>
            </FormGroup>
        </Form>
    );
}