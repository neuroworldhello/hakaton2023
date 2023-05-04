import React, {useState} from "react";
import {Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";
import {AddPracticeDialogContent} from "./AddPracticeDialogContent";
import axios from "axios";
import ButtonComponent from "./Button";

export function PracticeDialog({dialogOpen, setDialogOpen}) {
    const [practice, setPractice] = useState({});

    const handleDialogClose = () => {
        setDialogOpen(false);
    }

    const handleSaveButtonClick = () => {
        setDialogOpen(false);
        axios.post("http://localhost:8080/api/practice", practice)
            .then(response => console.log(response.data))
            .catch(error => console.log(error))
        console.log(practice)
    }

    return (
        <Dialog
            open={dialogOpen}
            onClose={handleDialogClose}
            maxWidth="sm"
            fullWidth={true}
        >
            <DialogTitle>Добавление практики</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    <AddPracticeDialogContent practice={practice} setPractice={setPractice}/>
                </DialogContentText>
            </DialogContent>
            <DialogActions>
                <ButtonComponent onClick={handleSaveButtonClick}>Добавить</ButtonComponent>
            </DialogActions>
        </Dialog>
    )
}