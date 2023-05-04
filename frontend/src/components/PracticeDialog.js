import React, {useState} from "react";
import {Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";
import {AddPracticeDialogContent} from "./AddPracticeDialogContent";
import {toastOptions} from "./consts";
import {toast} from "react-toastify";
import Button from "./Button";

export function PracticeDialog({dialogOpen, setDialogOpen}) {
    const [practice, setPractice] = useState({});

    const handleDialogClose = () => {
        setDialogOpen(false);
    }

    const handleSaveButtonClick = () => {
        setDialogOpen(false);

        const requestOptions = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(practice)
        };

        fetch("/api/practices", requestOptions)
            .then((response) => {
                if (response.ok) {
                    toast('Практика сохранена', toastOptions)
                } else {
                    throw new Error('Something went wrong')
                }
            })
            .catch(() => toast.error('Ошибка сохранения практики', toastOptions));
        setPractice({})
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