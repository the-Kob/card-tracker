import axios from "axios";

export async function getImageURL(file) {
    let url;

    try{
        const body = new FormData();
        body.set("key", "51a917bb3f38704b283b04d0a50708f6")
        body.append("image", file);

        console.log(body);

        const response = await axios({
            method: 'post',
            url: 'https://api.imgbb.com/1/upload',
            data: body
        })

        url = response.data.data.url;
    } catch {
        url = "https://i.ibb.co/zJ4Wsdc/Placeholder-view-vector-svg.png";
    }

    return url;
}