import React, { useCallback, useState } from "react";
import {Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";
import {AddPracticeDialogContent} from "./AddPracticeDialogContent";
import {toastOptions} from "./consts";
import {toast} from "react-toastify";
import Button from "./Button";
import axios from "axios";

export function PracticeDialog({dialogOpen, setDialogOpen, handleSearch}) {
    const [practice, setPractice] = useState({});

    const handleDialogClose = useCallback(() => {
        setDialogOpen(false);
    }, [setDialogOpen]);

    const handleSaveButtonClick = useCallback(() => {
        setDialogOpen(false);

        axios.post("/api/practices", practice)
            .then(() => toast('Практика ' + practice.name + ' сохранена', toastOptions))
            .then(handleSearch)
            .catch(() => toast.error('Ошибка сохранения практики', toastOptions));
        setPractice({})
    }, [practice, setDialogOpen, handleSearch]);

    return (
        <Dialog
            open={dialogOpen}
            onClose={handleDialogClose}
            maxWidth="sm"
            fullWidth={true}
        >
            <DialogTitle>Добавление практики</DialogTitle>
            <DialogContent className='pb-0'>
                <DialogContentText>
                    <AddPracticeDialogContent practice={practice} setPractice={setPractice}/>
                </DialogContentText>
            </DialogContent>
            <DialogActions className='pr-20 py-10'>
                <Button
                    onClick={handleSaveButtonClick}
                    disabled={!practice.name || !practice.team || !practice.category || !practice.documentLink}
                >
                    Добавить
                </Button>
            </DialogActions>
        </Dialog>
    )
}