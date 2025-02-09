import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';

import {MemberProvider} from "./context/MemberContext.jsx";

import Login from "./components/Login.jsx"
import Home from "./components/Home.jsx";
import MyExchange from './components/MyExchange.jsx';
import MyInsight from './components/MyInsight.jsx';
import DetailInsight from './components/DetailInsight.jsx'
import CreateInsight from "./components/CreateInsight.jsx";
import Layout from "./Layout.jsx";

export default function App() {
    return (
        <MemberProvider>
            <Router>
                <Layout>
                    <Routes>
                        <Route path="/login" element={<Login />} />
                        <Route path="/" element={<Home />} />
                        <Route path="/my-exchange" element={<MyExchange />} />
                        <Route path="/my-insight" element={<MyInsight />} />
                        <Route path="/insight/:id" element={<DetailInsight />} />
                        <Route path="/insight/create" element={<CreateInsight />} />
                    </Routes>
                </Layout>
            </Router>
        </MemberProvider>

    );
}
