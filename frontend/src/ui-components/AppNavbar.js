import {Navbar, NavbarBrand} from "reactstrap";

export default function AppNavbar() {
    return (
        <Navbar color="dark" dark expand="md">
            <NavbarBrand href="/">Collections</NavbarBrand>
        </Navbar>
    );
}