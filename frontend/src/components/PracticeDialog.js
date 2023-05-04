import React, {useState} from "react";
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";
import {AddPracticeDialogContent} from "./AddPracticeDialogContent";

export function PracticeDialog({dialogOpen, setDialogOpen}) {
    const [practice, setPractice] = useState({});

    const handleDialogClose = () => {
        setDialogOpen(false);
    }

    const handleSaveButtonClick = () => {
        setDialogOpen(false);
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
                <Button onClick={handleSaveButtonClick}>Добавить</Button>
            </DialogActions>
        </Dialog>
    )
}